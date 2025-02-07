/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.nativex.type;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.NativeTestContext;
import org.springframework.aot.TestTypeSystem;
import org.springframework.nativex.hint.AccessBits;
import org.springframework.nativex.type.entities.ConcreteType;
import org.springframework.nativex.type.entities.EntityWithAnnotations;
import org.springframework.nativex.type.entities.Order;
import org.springframework.nativex.type.entities.WithFinalField;
import org.springframework.nativex.type.entities.WithTypesInMethods;

/**
 * @author Christoph Strobl
 */
class TypeProcessorTests {

	private TestTypeSystem typeSystem;
	private NativeTestContext nativeContext;
	private TypeProcessor processor;

	private List<String> capturedTypes;
	private List<String> capturedAnnotations;

	@BeforeEach
	void beforeEach() {

		typeSystem = new TestTypeSystem();
		nativeContext = new NativeTestContext(typeSystem);

		capturedTypes = new ArrayList<>();
		capturedAnnotations = new ArrayList<>();

		processor = new TypeProcessor(
				(type, context) -> {

					// types we discovered
					context.addReflectiveAccess(type, new AccessDescriptor(AccessBits.FULL_REFLECTION));
					capturedTypes.add(type.getDottedName());
				},
				(type, context) -> {

					// annotations we discovered
					context.addReflectiveAccess(type, new AccessDescriptor(AccessBits.ANNOTATION));
					capturedAnnotations.add(type.getDottedName());
				}
		);
	}

	@Test
	void detectsUsedTypesAndAnnotations() {

		processor.process(typeSystem.resolve(Order.class), nativeContext);

		assertThat(capturedTypes).containsExactlyInAnyOrder("org.springframework.nativex.type.entities.Order", "java.lang.Long", "java.lang.String", "java.util.List", "org.springframework.nativex.type.entities.LineItem", "java.util.Date");
		assertThat(capturedAnnotations).contains("javax.persistence.Entity", "javax.persistence.ManyToOne", "javax.persistence.OneToMany", "javax.persistence.GeneratedValue", "org.springframework.nativex.type.entities.SomeAnnotation", "javax.annotation.Nullable", "org.springframework.beans.factory.annotation.Value");
	}

	@Test
	void ignoresAnnotationsNotReachable() {

		typeSystem.excludePackages("javax.annotation"); //  @Nullable should not be there

		processor.process(typeSystem.resolve(Order.class), nativeContext);

		assertThat(capturedTypes).containsExactlyInAnyOrder("org.springframework.nativex.type.entities.Order", "java.lang.Long", "java.lang.String", "java.util.List", "org.springframework.nativex.type.entities.LineItem", "java.util.Date");
		assertThat(capturedAnnotations).contains("org.springframework.nativex.type.entities.SomeAnnotation").doesNotContain("javax.annotation.Nullable");
	}

	@Test
	void includesFieldsBasedOnFilter() {

		processor.filterFields(field -> field.isFinal())
				.process(typeSystem.resolve(WithFinalField.class), nativeContext);

		assertThat(capturedTypes).containsExactlyInAnyOrder("org.springframework.nativex.type.entities.WithFinalField", "java.lang.String").doesNotContain("java.lang.Long");
	}

	@Test
	void includesAnnotationsBasedOnFilter() {

		processor.filterAnnotations(annotation -> annotation.isPartOfDomain("javax."))
				.process(typeSystem.resolve(EntityWithAnnotations.class), nativeContext);

		assertThat(capturedAnnotations)
				.containsExactlyInAnyOrder("javax.persistence.Entity", "javax.persistence.GeneratedValue")
				.doesNotContain("org.springframework.nativex.type.entities.SomeAnnotation");
	}

	@Test
	void excludesAnnotationsBasedOnFilter() {

		processor.skipAnnotationsMatching(annotation -> annotation.isPartOfDomain("javax."))
				.process(typeSystem.resolve(EntityWithAnnotations.class), nativeContext);

		assertThat(capturedAnnotations)
				.containsExactly("org.springframework.nativex.type.entities.SomeAnnotation")
				.doesNotContain("javax.persistence.Entity", "javax.persistence.GeneratedValue");
	}

	@Test
	void excludesFieldsBasedOnFilter() {

		processor.skipFieldsMatching(field -> field.isFinal())
				.process(typeSystem.resolve(WithFinalField.class), nativeContext);

		assertThat(capturedTypes).containsExactlyInAnyOrder("org.springframework.nativex.type.entities.WithFinalField", "java.lang.Long").doesNotContain("java.lang.String");
	}

	@Test
	void capturesMethodParameterTypes() {

		processor.process(typeSystem.resolve(WithTypesInMethods.class), nativeContext);

		assertThat(capturedTypes).containsExactlyInAnyOrder("org.springframework.nativex.type.entities.WithTypesInMethods", "java.util.Date", "java.util.List", "java.lang.Long", "java.lang.Integer", "java.lang.String");
	}

	@Test
	void usesTypeSystemScanToProcessTypesMatching() {

		List<HintDeclaration> hints = processor
				.skipAnnotationInspection()
				.skipMethodInspection()
				.skipFieldInspection()
				.use(typeSystem).toProcessTypesMatching(type -> type.getName().endsWith("NotAnEntity"));

		assertThat(hints).hasSize(1);
		assertThat(capturedTypes).containsExactly("org.springframework.nativex.type.entities.NotAnEntity");
	}

	@Test
	void capturesSignatureTypesOfType() {

		processor.process(typeSystem.resolve(ConcreteType.class), nativeContext);

		assertThat(capturedTypes)
				.containsExactlyInAnyOrder("org.springframework.nativex.type.entities.ConcreteType", "org.springframework.nativex.type.entities.AbstractType", "org.springframework.nativex.type.entities.InterfaceType")
				.doesNotContain("java.lang.Object");
	}

	@Test
	void excludesJavaLangObjectByDefault() {

		BiConsumer<Type, NativeContext> consumer = Mockito.mock(BiConsumer.class);
		TypeProcessor.namedProcessor("test").onTypeDiscovered(consumer).use(typeSystem).toProcessType(typeSystem.resolve(Object.class));

		Mockito.verifyNoInteractions(consumer);
	}
}

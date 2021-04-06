/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.kafka;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Thing2 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4143636050760686136L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Thing2\",\"namespace\":\"com.example.kafka\",\"fields\":[{\"name\":\"stringField\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"intField\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Thing2> ENCODER =
      new BinaryMessageEncoder<Thing2>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Thing2> DECODER =
      new BinaryMessageDecoder<Thing2>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Thing2> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Thing2> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Thing2>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Thing2 to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Thing2 from a ByteBuffer. */
  public static Thing2 fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String stringField;
  @Deprecated public int intField;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Thing2() {}

  /**
   * All-args constructor.
   * @param stringField The new value for stringField
   * @param intField The new value for intField
   */
  public Thing2(java.lang.String stringField, java.lang.Integer intField) {
    this.stringField = stringField;
    this.intField = intField;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return stringField;
    case 1: return intField;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: stringField = (java.lang.String)value$; break;
    case 1: intField = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'stringField' field.
   * @return The value of the 'stringField' field.
   */
  public java.lang.String getStringField() {
    return stringField;
  }

  /**
   * Sets the value of the 'stringField' field.
   * @param value the value to set.
   */
  public void setStringField(java.lang.String value) {
    this.stringField = value;
  }

  /**
   * Gets the value of the 'intField' field.
   * @return The value of the 'intField' field.
   */
  public java.lang.Integer getIntField() {
    return intField;
  }

  /**
   * Sets the value of the 'intField' field.
   * @param value the value to set.
   */
  public void setIntField(java.lang.Integer value) {
    this.intField = value;
  }

  /**
   * Creates a new Thing2 RecordBuilder.
   * @return A new Thing2 RecordBuilder
   */
  public static com.example.kafka.Thing2.Builder newBuilder() {
    return new com.example.kafka.Thing2.Builder();
  }

  /**
   * Creates a new Thing2 RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Thing2 RecordBuilder
   */
  public static com.example.kafka.Thing2.Builder newBuilder(com.example.kafka.Thing2.Builder other) {
    return new com.example.kafka.Thing2.Builder(other);
  }

  /**
   * Creates a new Thing2 RecordBuilder by copying an existing Thing2 instance.
   * @param other The existing instance to copy.
   * @return A new Thing2 RecordBuilder
   */
  public static com.example.kafka.Thing2.Builder newBuilder(com.example.kafka.Thing2 other) {
    return new com.example.kafka.Thing2.Builder(other);
  }

  /**
   * RecordBuilder for Thing2 instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Thing2>
    implements org.apache.avro.data.RecordBuilder<Thing2> {

    private java.lang.String stringField;
    private int intField;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.kafka.Thing2.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.stringField)) {
        this.stringField = data().deepCopy(fields()[0].schema(), other.stringField);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.intField)) {
        this.intField = data().deepCopy(fields()[1].schema(), other.intField);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Thing2 instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.kafka.Thing2 other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.stringField)) {
        this.stringField = data().deepCopy(fields()[0].schema(), other.stringField);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.intField)) {
        this.intField = data().deepCopy(fields()[1].schema(), other.intField);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'stringField' field.
      * @return The value.
      */
    public java.lang.String getStringField() {
      return stringField;
    }

    /**
      * Sets the value of the 'stringField' field.
      * @param value The value of 'stringField'.
      * @return This builder.
      */
    public com.example.kafka.Thing2.Builder setStringField(java.lang.String value) {
      validate(fields()[0], value);
      this.stringField = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'stringField' field has been set.
      * @return True if the 'stringField' field has been set, false otherwise.
      */
    public boolean hasStringField() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'stringField' field.
      * @return This builder.
      */
    public com.example.kafka.Thing2.Builder clearStringField() {
      stringField = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'intField' field.
      * @return The value.
      */
    public java.lang.Integer getIntField() {
      return intField;
    }

    /**
      * Sets the value of the 'intField' field.
      * @param value The value of 'intField'.
      * @return This builder.
      */
    public com.example.kafka.Thing2.Builder setIntField(int value) {
      validate(fields()[1], value);
      this.intField = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'intField' field has been set.
      * @return True if the 'intField' field has been set, false otherwise.
      */
    public boolean hasIntField() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'intField' field.
      * @return This builder.
      */
    public com.example.kafka.Thing2.Builder clearIntField() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Thing2 build() {
      try {
        Thing2 record = new Thing2();
        record.stringField = fieldSetFlags()[0] ? this.stringField : (java.lang.String) defaultValue(fields()[0]);
        record.intField = fieldSetFlags()[1] ? this.intField : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Thing2>
    WRITER$ = (org.apache.avro.io.DatumWriter<Thing2>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Thing2>
    READER$ = (org.apache.avro.io.DatumReader<Thing2>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}

package application.converter;

import jakarta.persistence.AttributeConverter;

public final class DatabaseEncryptor implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        // TODO: Add encryption.
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // TODO: Add decryption.
        return dbData;
    }
}
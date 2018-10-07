package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderFileReaderTest {

    @Test
    void checkFileExtension() {
        assertEquals(checkFileExtensionInvoke("<requests>"), FileExtension.XML);
        assertEquals(checkFileExtensionInvoke("Client_Id,Request_id,Name,Quantity,Price "), FileExtension.CSV);
        assertEquals(checkFileExtensionInvoke("Client_Id,Request_id"), FileExtension.CSV);
        assertEquals(checkFileExtensionInvoke("ADASDAdasdjkla<askdjaldk>da"), FileExtension.NO_EXTENSION);

    }

    private FileExtension checkFileExtensionInvoke(String firstLine) {
        try {
            Method checkFileExtMethod = OrderFileReader.class.getDeclaredMethod("checkFileExtension", String.class);
            checkFileExtMethod.setAccessible(true);
            FileExtension fileExtension = (FileExtension) checkFileExtMethod.invoke(null, firstLine);
            checkFileExtMethod.setAccessible(false);
            return fileExtension;
        } catch (ReflectiveOperationException roex) {
            roex.printStackTrace();
        }
        return null;
    }
}
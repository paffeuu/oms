package service;

import model.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderFileReaderTest {
    OrderFileReader orderFileReader;

    @Test
    void read() {
        String[] files = new String[]{"example.csv"};
        orderFileReader = new OrderFileReader(files);
        ArrayList<Order> correctList = new ArrayList<>();
        correctList.add(new Order("1",1,"Bułka",1,10.00));
        correctList.add(new Order("1",1,"Chleb",2,15.00));
        correctList.add(new Order("1",2,"Chleb",5,15.00));
        correctList.add(new Order("2",1,"Chleb",1,10.00));
        assertArrayEquals(correctList.toArray(), orderFileReader.read().toArray());

        files = new String[]{"example.xml"};
        orderFileReader = new OrderFileReader(files);

        files = new String[]{"example.csv", "example.xml"};
        orderFileReader = new OrderFileReader(files);
    }

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
package xmlTest;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "objects")
public class UserData {
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;
    private List<ObjectData> objects;

    @Data
    public static class ObjectData {
        private String name;
        private String email;
        private String gender;
        private String status;
        private IDWrap id;

        public static class IDWrap {
            private int value;
            private String type;
        }
    }
}

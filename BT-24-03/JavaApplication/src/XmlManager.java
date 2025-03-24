import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class XmlManager {
    private Map<String, String> elementMap = new LinkedHashMap<>();

    public void createElement(String tagName, String content) {
        elementMap.put(tagName, content);
    }

    public void updateElement(String tagName, String newContent) {
        elementMap.put(tagName, newContent);
    }

    public void deleteElement(String tagName) {
        elementMap.remove(tagName);
    }

    public Map<String, String> getElements() {
        return elementMap;
    }

    public String getContentByTag(String tagName) {
        return elementMap.getOrDefault(tagName, "");
    }

}

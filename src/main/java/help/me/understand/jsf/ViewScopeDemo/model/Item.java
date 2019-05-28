package help.me.understand.jsf.ViewScopeDemo.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Item implements Serializable {
    private Long id;
    private String value;

    public Item() {
    }

    public Item(Long _id, String _value) {
        this.id = _id;
        this.value = _value;
    }
}

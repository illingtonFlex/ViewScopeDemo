package help.me.understand.jsf.ViewScopeDemo.controller;

import help.me.understand.jsf.ViewScopeDemo.model.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class ViewScopedController implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ViewScopedController.class);
    private List<Item> list;
    private Item item = new Item();
    private boolean edit;

    @PostConstruct
    public void init() {
        // list = dao.list();
        // Actually, you should retrieve the list from DAO. This is just for demo.
        list = new ArrayList<Item>();
        list.add(new Item(1L, "item1"));
        list.add(new Item(2L, "item2"));
        list.add(new Item(3L, "item3"));
    }

    public void add() {
        // dao.create(item);
        // Actually, the DAO should already have set the ID from DB. This is just for demo.
        item.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(item);
        item = new Item(); // Reset placeholder.
    }

    public void doEdit(Item item) {
        this.item = item;
        edit = true;
    }

    public void save() {
        // dao.update(item);
        item = new Item(); // Reset placeholder.
        edit = false;
    }

    public void delete(Item item) {
        // dao.delete(item);
        list.remove(item);
    }

    public List<Item> getList() {
        return list;
    }

    public Item getItem() {
        return item;
    }

    public boolean isEdit() {
        return edit;
    }

    public void doException() throws Exception {
        throw new Exception("Something bad happened!");
    }

    // Other getters/setters are actually unnecessary. Feel free to add them though.
}
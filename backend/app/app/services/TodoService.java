package services;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import dao.TodoDao;
import domain.TodoDO;


public class TodoService {
    @Inject
    private TodoDao todoDao;

    public List<TodoDO> getAll() {
        List<TodoDO> todoDOList = todoDao.findAll(100);
        return todoDOList;
    }

    public TodoDO getById(int id) {
        TodoDO todoDO = todoDao.find(id);
        if (todoDO == null) {
            return null;
        }

        return todoDO;
    }

    public TodoDO create(TodoDO todoDO) {
        todoDO = todoDao.create(todoDO);
        return todoDO;
    }

    public TodoDO update(TodoDO todoDO, int id) {
        TodoDO fromDb = todoDao.find(id);
        if (fromDb == null) {
            return null;
        }

        fromDb.setTitle(todoDO.getTitle());
        fromDb.setText(todoDO.getText());
        fromDb = todoDao.update(fromDb);

        return fromDb;
    }

    public void delete(int id) {
        todoDao.delete(id);
    }
}
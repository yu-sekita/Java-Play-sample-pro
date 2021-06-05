package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import domain.TodoDO;
import services.TodoService;


public class TodosController extends Controller {
    @Inject
    private TodoService todoService;

    public Result get(Integer id) {
        TodoDO todo = todoService.getById(id);
        if (todo != null) {
            return ok(Json.toJson(todo));
        }

        return notFound();
    }

    public Result getAll() {
        return ok(Json.toJson(todoService.getAll()));
    }

    public Result create(Http.Request request) {
        JsonNode jsonData = request.body().asJson();
        try {
            TodoDO todoDO = Json.fromJson(jsonData, TodoDO.class);
            System.out.println(todoDO.getTitle());
            System.out.println(todoDO.getText());
            todoDO = todoService.create(todoDO);
            return ok(Json.toJson(todoDO));
        } catch (RuntimeException e) {
            // Most probably invalid todo data
            return badRequest(request.body().asJson());
        }
    }

    public Result update(Http.Request request, Integer id) {
        JsonNode jsonData = request.body().asJson();
        try {
            TodoDO todoDO = Json.fromJson(jsonData, TodoDO.class);
            TodoDO todo = todoService.update(todoDO, id);
            if (todo != null) {
                return ok(Json.toJson(todo));
            }
            return notFound();
        } catch (RuntimeException e) {
            return badRequest(request.body().asJson());
        }
    }

    public Result delete(Integer id) {
        todoService.delete(id);
        return ok();
    }
}
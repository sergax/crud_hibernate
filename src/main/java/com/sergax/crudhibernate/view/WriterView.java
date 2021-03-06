package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.PostController;
import com.sergax.crudhibernate.controller.WriterController;
import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.util.Messages;

import java.util.*;

public class WriterView extends GeneralView {
    private final WriterController writerController;
    private final PostController postController;
    private Scanner sc;

    public WriterView(WriterController writerController, PostController postController, Scanner sc) {
        this.message = actionList;
        this.writerController = writerController;
        this.postController = postController;
        this.sc = sc;
    }

    private final String actionList = """
                                      Choose action by writers :\s
                                      1. Create\s
                                      2. Update\s
                                      3. Delete\s
                                      4. Get list\s
                                      5. Exit\s
                                      """;

    private final String printActionList = "List of writers : \n" + "ID; name; Writers";
    private final String createActionList = "Create writers . \n" + Messages.NAME.getMessage();
    private final String updateActionList = "Update writers . \n" + Messages.ID.getMessage();
    private final String deleteActionList = "Delete writers . \n" + Messages.ID.getMessage();

    @Override
    public void show() {
        boolean isExit = false;
        do {
            print();
            System.out.println(actionList);
            String response = sc.next();
            switch (response) {
                case "1" -> create();
                case "2" -> edit();
                case "3" -> delete();
                case "4" -> print();
                case "5" -> isExit = true;
                default -> System.out.println(Messages.ERROR_INPUT.getMessage());
            }
        } while (!isExit);
    }

    @Override
    void create() {
        Writer writer = new Writer();
        System.out.println(createActionList);
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        writer.setName(name);
        writerController.create(writer);
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void edit() {
        writerController.update(updateWriters());
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    private Writer updateWriters() {
        System.out.println(updateActionList);
        Long id = sc.nextLong();
        Writer writer = writerController.getById(id);
        if (writer != null) {
            System.out.println(Messages.UPDATE_WRITERS.getMessage());
            Long response = sc.nextLong();
            while (true) {
                if (response == 1) {
                    System.out.println(Messages.NAME.getMessage());
                    sc = new Scanner(System.in);
                    String name = sc.nextLine();
                    writer.setName(name);
                    break;
                } else if (response == 2) {
                    writer.setPostList(selectPosts());
                    break;
                }
            }
        }
        return writer;
    }

    private List<Post> selectPosts() {
        List<Post> postList = new ArrayList<>();
        System.out.println(Messages.POST.getMessage());
        Long input = sc.nextLong();
        if (input != 0) {
            postList = (List<Post>) postController.getById(input);
        }
        return postList;
    }

    @Override
    void delete() {
        System.out.println(deleteActionList);
        Long id = sc.nextLong();
        writerController.deleteById(id);
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void print() {
        System.out.println(printActionList);
        System.out.println(writerController.getAll());
    }
}

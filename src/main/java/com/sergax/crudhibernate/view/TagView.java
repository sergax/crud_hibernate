package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.TagController;
import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.HibernateRepoImpl.TagRepoImpl;
import com.sergax.crudhibernate.util.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TagView extends GeneralView{
    private final TagController tagController;
    private Scanner sc;

    public TagView(TagController tagController, Scanner sc) {
        this.message = actionList;
        this.tagController = tagController;
        this.sc = sc;
    }

    private final String actionList = "Choose action by tags : \n" +
            "1. Create \n" +
            "2. Update \n" +
            "3. Delete \n" +
            "4. Get list \n" +
            "5. Exit \n";

    private final String createActionList = "Create tag . \n" + Messages.NAME.getMessage();
    private final String updateActionList = "Update tag . \n" + Messages.ID.getMessage();
    private final String deleteActionList = "Delete tag . \n" + Messages.ID.getMessage();

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
    public void create() {
        System.out.println(createActionList);
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        tagController.create(new Tag(null, name));
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    public void edit() {
        System.out.println(updateActionList);
        Long id = Long.parseLong(String.valueOf(sc.nextLong()));
        System.out.println(Messages.NAME.getMessage());
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        tagController.update(new Tag(id, name));
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    public void delete() {
        System.out.println(deleteActionList);
        Long id = sc.nextLong();
        // add method that will be remove object by "id"
        tagController.deleteById(new Tag(id, ""));
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    public void print() {
        String printActionList = "List of tags : \n" + "ID; name";
        System.out.println(printActionList);
        System.out.println(tagController.getAll());
    }
}

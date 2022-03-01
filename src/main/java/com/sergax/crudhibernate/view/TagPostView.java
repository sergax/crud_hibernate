package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.TagPostController;
import com.sergax.crudhibernate.model.TagPost;
import com.sergax.crudhibernate.util.Messages;

import java.util.List;
import java.util.Scanner;

public class TagPostView {
    private final TagPostController tagPostController;
    private Messages message;
    private Scanner sc;

    public TagPostView(TagPostController tagPostController, Scanner sc) {
        this.tagPostController = tagPostController;
        this.sc = sc;
    }

    private final String actionList = """
                                      Choose action by tag_post :\s
                                      1. Create\s
                                      2. Exit\s
                                      """;

    private final String createActionList = "Create tag_post . \n";

    public void show() {
        boolean isExit = false;
        do {
            print();
            System.out.println(actionList);
            String response = sc.next();
            switch (response) {
                case "1" -> create();
                case "2" -> isExit = true;
                default -> System.out.println(Messages.ERROR_INPUT.getMessage());
            }

        } while (!isExit);
    }

    public void create() {
        TagPost tagPost = new TagPost();
        System.out.println(createActionList + tagPostController.getAll());
        sc = new Scanner(System.in);
        System.out.println(Messages.TAG.getMessage());
        Long tagId = sc.nextLong();
        System.out.println(Messages.POST.getMessage());
        Long postId = sc.nextLong();
        tagPost.setTag_id(tagId);
        tagPost.setPost_id(postId);
        tagPostController.create(new TagPost(tagId, postId));
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    private List<Long> print() {
        return tagPostController.getAll();
    }
}

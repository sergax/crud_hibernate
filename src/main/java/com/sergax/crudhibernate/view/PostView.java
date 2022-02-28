package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.PostController;
import com.sergax.crudhibernate.controller.TagController;
import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.PostStatus;
import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.HibernateRepoImpl.TagRepoImpl;
import com.sergax.crudhibernate.util.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView extends GeneralView {
    private final PostController postController;
    private final TagController tagController;
    private PostStatus postStatus;
    private Scanner sc;

    public PostView(PostController postController, TagController tagController, Scanner sc) {
        this.message = actionList;
        this.postController = postController;
        this.tagController = tagController;
        this.sc = sc;
    }

    private final String actionList = """
                                      Choose action by posts :\s
                                      1. Create\s
                                      2. Update\s
                                      3. Delete\s
                                      4. Get list\s
                                      5. Exit\s
                                      """;

    private final String createActionList = "Create post . \n" + Messages.CONTENT.getMessage();
    private final String updateActionList = "Update post . \n" + Messages.ID.getMessage();
    private final String deleteActionList = "Delete post . \n" + Messages.ID.getMessage();

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
        Post post = new Post();
        System.out.println(createActionList);
        sc = new Scanner(System.in);
        String content = sc.nextLine();
        System.out.println(Messages.TAG.getMessage());
        Long tag_id = sc.nextLong();
        List<Tag> tagList = (List<Tag>) tagController.create(new Tag(tag_id,""));
        PostStatus status = selectStatus();
        post.setContent(content);
        post.setPoststatus(status);
        postController.create(new Post(null, content, tagList, status));
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    public void edit() {
        postController.update(updatePost());
        System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    public void delete() {
        try {
            System.out.println(deleteActionList);
            Long id = sc.nextLong();
            postController.deleteById(new Post(id, "", new ArrayList<>(), postStatus));
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void print() {
        String printActionList = "List of posts : \n" + "ID; content; Tags; status";
        System.out.println(printActionList);
        System.out.println(postController.getAll());
    }

    private Post updatePost() {
        System.out.println(updateActionList);
        Long id = sc.nextLong();
        System.out.println(Messages.CONTENT.getMessage());
        String content = sc.next();
        System.out.println(Messages.STATUS.getMessage());
        PostStatus status = selectStatus();
        Post post = postController.update(new Post(id, content, new ArrayList<>(), status));
        return post;
    }

    private List<Tag> selectTags() {
        System.out.println("Existing Tags" + tagController.getAll());
        List<Tag> tagList = new ArrayList<>();
        System.out.println(Messages.TAG.getMessage());
        Long input = sc.nextLong();
        if (input != 0) {
            tagList.add(tagController.getById(input));
        }
        return tagList;
    }

    private PostStatus selectStatus() {
        System.out.println(Messages.STATUS.getMessage());
        System.out.println("1. ACTIVE, \n" +
                "2. DELETED \n");
        while (true) {
            Long input = sc.nextLong();
            if (input == 1) {
                postStatus = PostStatus.ACTIVE;
                break;
            } else if (input == 2) {
                postStatus = PostStatus.DELETE;
                break;
            } else {
                System.out.println(Messages.ERROR_INPUT.getMessage());
                break;
            }
        }
        return postStatus;
    }
}

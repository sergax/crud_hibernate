package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.PostController;
import com.sergax.crudhibernate.controller.TagController;
import com.sergax.crudhibernate.controller.WriterController;
import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.PostStatus;
import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.repository.hibernateRepoImpl.PostRepoImpl;
import com.sergax.crudhibernate.util.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView extends GeneralView {
    private final PostController postController;
    private final TagController tagController;
    private final WriterController writerController;
    private PostStatus postStatus;
    private Scanner sc;

    public PostView(PostController postController, TagController tagController, WriterController writerController, Scanner sc) {
        this.writerController = writerController;
        this.message = actionList;
        this.postController = postController;
        this.tagController = tagController;
        this.sc = sc;
    }

    private final String actionList = """
                                      Choose action by posts :\s
                                      1. Create\s
                                      2. Choose id for Tag/Post\s
                                      3. Choose id for Writer\s
                                      4. Update\s
                                      5. Delete\s
                                      6. Get list\s
                                      7. Exit\s
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
                case "2" -> createTagPost();
                case "3" -> createWriterId();
                case "4" -> edit();
                case "5" -> delete();
                case "6" -> print();
                case "7" -> isExit = true;
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
        PostStatus status = selectStatus();
        post.setContent(content);
        post.setPoststatus(status);
        postController.create(post);
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
            postController.deleteById(id);
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
        Post post = postController.update(new Post(id, content, status));
        return post;
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

    private void createTagPost() {
        PostRepoImpl postRepo = new PostRepoImpl();
        System.out.println(Messages.TAG.getMessage());
        Long tagId = sc.nextLong();
        System.out.println(Messages.POST.getMessage());
        Long postId = sc.nextLong();
        postRepo.createTagPost(postId, tagId);
    }

    private void createWriterId() {
        PostRepoImpl postRepo = new PostRepoImpl();
        System.out.println(Messages.WRITER.getMessage());
        Long post_writer_id = sc.nextLong();
        System.out.println(Messages.POST.getMessage());
        Long postId = sc.nextLong();
        postRepo.createPostList(post_writer_id, postId);
    }
}

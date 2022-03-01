package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.PostController;
import com.sergax.crudhibernate.controller.TagController;
import com.sergax.crudhibernate.controller.TagPostController;
import com.sergax.crudhibernate.controller.WriterController;
import com.sergax.crudhibernate.util.Messages;

import java.util.Scanner;

public class ConsoleRunner {
    private GeneralView tagView;
    private GeneralView postView;
    private GeneralView writerView;
    private TagPostView tagPostView;

    private final Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {
        try {
            TagController tagController = new TagController();
            PostController postController = new PostController();
            WriterController writerController = new WriterController();
            TagPostController tagPostController = new TagPostController();

            tagView = new TagView(tagController, sc);
            postView = new PostView(postController, tagController, sc);
            writerView = new WriterView(writerController, postController, sc);
            tagPostView = new TagPostView(tagPostController, sc);

        } catch (Exception e) {
            System.err.println(Messages.DATA_DAMAGED.getMessage());
        }
    }

    public void run() {
        boolean isExit = false;
        do {
            System.out.println(Messages.ACTION_LIST.getMessage());
            String response = sc.next();
            switch (response) {
                case "1" -> tagView.show();
                case "2" -> postView.show();
                case "3" -> writerView.show();
                case "4" -> tagPostView.show();
                case "5" -> isExit = true;
                default -> System.out.println(Messages.ERROR_INPUT.getMessage());
            }
        } while (!isExit);
        sc.close();
    }

    public static void main(String[] args) {
        try {
            ConsoleRunner runner = new ConsoleRunner();
            runner.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

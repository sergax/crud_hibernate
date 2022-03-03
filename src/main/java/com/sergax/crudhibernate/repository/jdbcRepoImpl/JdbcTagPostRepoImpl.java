package com.sergax.crudhibernate.repository.jdbcRepoImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTagPostRepoImpl {
    private final String SQL_ADD_TAGS = "insert into tag_post " +
            "(tag_id, post_id) " +
            "values " +
            "(?, ?)";

    public void create(Long postId, Long tagId) {
        try (PreparedStatement preparedStatementAddTags = JdbcUtil.getConnection().prepareStatement(SQL_ADD_TAGS)) {
//            for (Long tagId : getIdTags(post)) {
                preparedStatementAddTags.setLong(1, tagId);
                preparedStatementAddTags.setLong(2, postId);
                preparedStatementAddTags.executeUpdate();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Long> getIdTags(Post post) {
        List<Long> tagsId = new ArrayList<>();
        for (Tag postsTag : post.getTagList()) {
            tagsId.add(postsTag.getTag_id());
        }
        return tagsId;
    }
}

package net.oceandepth.journalApp.repository;

import net.oceandepth.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("hamid"));
        query.addCriteria(Criteria.where("setimentAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }


}

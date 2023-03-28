package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.model.KeywordsCountResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

@Service
public class KeywordsCountService {

    private final MongoTemplate mongoTemplate;

    public KeywordsCountService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     *
     * @param patientId
     * @return
     * @throws JsonProcessingException
     */
    public Integer countKeywords(Integer patientId) throws JsonProcessingException {

        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> collection = database.getCollection("notes");

        String keywordsRegex = "(feeling|reaction)(?i)";

        // l'aggregation renvoie un document du type {_id: "", total: 1}
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(

                // 1- Selection des notes du patient
                new Document("$match",
                    new Document("patient_id", patientId)),


                new Document("$addFields",
                    // 2 - on split les mots de chaque document dans un array
                    new Document("words",
                        new Document("$split", Arrays.asList("$content", " ")))
                    // 3 - containsKeyword = true si le document contient un des mots-clés
                    .append("containsKeyword",
                        new Document("$regexMatch",
                            new Document("input", "$content")
                            .append("regex", Pattern.compile(keywordsRegex))))),

                // 4 - on exclue les documents qui ne contiennent pas de mot-clé
                new Document("$match",
                    new Document("containsKeyword", true)),

                // 5 - on sépare les mots du contenu de chaque document
                new Document("$unwind",
                    new Document("path", "$words")),

                // 6 - on ne garde que les mots-clés
                new Document("$match",
                    new Document("words", Pattern.compile(keywordsRegex))),

                // 7 - on les compte
                new Document("$group",
                    new Document("_id",
                        new Document("$toLower", "$words"))
                    .append("count",
                        new Document("$sum", 1L))),

                // 8 - on fait la somme globale des comptes de chaque mot-clé
                new Document("$group",
                    new Document("_id", "")
                    .append("total",
                        new Document("$sum", "$count")))));



        if (result.into(new ArrayList<>()).isEmpty()) {
            return 0;
        }
        else {
            ObjectMapper mapper = new ObjectMapper();
            String rr = result.first().toJson();
            KeywordsCountResult r = mapper.readValue(rr, KeywordsCountResult.class);

            return r.getTotal();
        }
    }
}

// Here is raw query, for information :
/*
[
    {
        $match: {
            patient_id: 1,
        },
    },
    {
        $addFields: {
            words: {
                // splite les mots dans un array
                $split: ["$content", " "],
            },
            containsKeyword: {
                // true si le document contient un des mots-clés
                $regexMatch: {
                input: "$content",
                regex: /(feeling|reaction)/i,
                },
            },
        },
    },
    {
        // exclue les documents qui ne contiennent pas de mot-clé
        $match: {
        containsKeyword: true,
        },
    },
    {
        // sépare les mots du contenu de chaque document
        $unwind: {
        path: "$words",
        },
    },
    {
        // on ne garde que les mots-clés
        $match: {
            words: /(feeling|reaction)/i,
        },
    },
    {
        // on les compte
        $group: {
            _id: {
                $toLower: "$words",
            },
            count: {
                $sum: 1,
            },
        },
    },
    {
        // et on fait la somme globale des comptes de chaque mot-clé
        $group: {
            _id: "",
            total: {
                $sum: "$count",
            },
        },
    },
]
*/
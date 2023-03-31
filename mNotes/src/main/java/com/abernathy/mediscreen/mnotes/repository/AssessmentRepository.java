package com.abernathy.mediscreen.mnotes.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.abernathy.mediscreen.mnotes.model.TriggersCountResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

@Repository
public class AssessmentRepository {

    private final MongoTemplate mongoTemplate;

    public AssessmentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     *
     * @return
     */
    public int getTriggersCount(Integer patientId, String triggersRegex) {

        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> collection = database.getCollection("notes");


        AggregateIterable<Document> result =  collection.aggregate(Arrays.asList(

                // 1- Selection des notes du patient
                new Document("$match",
                        new Document("patient_id", patientId)),


                new Document("$addFields",
                        // 2 - on split les mots de chaque document dans un array
                        new Document("words",
                                new Document("$split", Arrays.asList("$content", " ")))
                                // 3 - on crée containsKeyword (= true si le document contient un des mots-clés)
                                .append("containsKeyword",
                                        new Document("$regexMatch",
                                                new Document("input", "$content")
                                                        .append("regex", Pattern.compile(triggersRegex))))),

                // 4 - on exclue les documents qui ne contiennent pas de trigger
                new Document("$match",
                        new Document("containsKeyword", true)),

                // 5 - on sépare les mots du contenu de chaque document
                new Document("$unwind",
                        new Document("path", "$words")),

                // 6 - on ne garde que les triggers
                new Document("$match",
                        new Document("words", Pattern.compile(triggersRegex))),

                // 7 - on les compte
                new Document("$group",
                        new Document("_id",
                                new Document("$toLower", "$words"))
                                .append("count",
                                        new Document("$sum", 1L))),

                // 8 - on fait la somme globale des comptes de tous les triggers
                new Document("$group",
                        new Document("_id", "")
                                .append("total",
                                        new Document("$sum", "$count")))));

        if (result.into(new ArrayList<>()).isEmpty()) {
            return 0;
        }
        else {
            // Extract total form result
            Document resultDocument = result.first();
            ObjectMapper mapper = new ObjectMapper();
            try {
                TriggersCountResult r = mapper.readValue(resultDocument.toJson(), TriggersCountResult.class);
                return r.getTotal();
            } catch (JsonProcessingException ex) {
                return 0;
            }
        }
    }
}

/* For information, here's the raw mongodb query */
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
                /* splite les mots dans un array *-/
                $split: ["$content", " "],
            },
            containsKeyword: {
                /* true si le document contient un des triggers *-/
                $regexMatch: {
                    input: "$content",
                    regex: /(trigger1|trigger2|trigger3)/i,
                },
            },
        },
    },
    {
        /* exclue les documents qui ne contiennent pas de triggers *-/
        $match: {
            containsKeyword: true,
        },
    },
    {
        /* sépare les mots du contenu de chaque document *-/
        $unwind: {
            path: "$words",
        },
    },
    {
        /* on ne garde que les triggers  *-/
        $match: {
            words: /(feeling|reaction)/i,
        },
    },
    {
        /* on les compte *-/
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
        /* et on fait la somme globale des comptes de de tous les triggers *-/
        $group: {
            _id: "",
            total: {
                $sum: "$count",
            },
        },
    },
]
*/
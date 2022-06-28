package com.example.data.testUtils

import com.example.data.entity.CharacterListResponse

object GetRepositoryFakeDataFromStringJsonFile {
    fun getOneMarvelCharacterList(): CharacterListResponse {
        val jsonConverter = JSONConverter<CharacterListResponse>()
        return jsonConverter.jsonToObject(res, CharacterListResponse::class.java)
    }
}

val res = "{\n" +
        "    \"code\": 200,\n" +
        "    \"status\": \"Ok\",\n" +
        "    \"copyright\": \"© 2022 MARVEL\",\n" +
        "    \"attributionText\": \"Data provided by Marvel. © 2022 MARVEL\",\n" +
        "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2022 MARVEL</a>\",\n" +
        "    \"etag\": \"a0e24d410b8c2da8d79c7da95dd6b4eeeb5b2eea\",\n" +
        "    \"data\": {\n" +
        "        \"offset\": 0,\n" +
        "        \"limit\": 20,\n" +
        "        \"total\": 1,\n" +
        "        \"count\": 1,\n" +
        "        \"results\": [\n" +
        "            {\n" +
        "                \"id\": 1017100,\n" +
        "                \"name\": \"A-Bomb (HAS)\",\n" +
        "                \"description\": \"Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\n" +
        "                \"modified\": \"2013-09-18T15:54:04-0400\",\n" +
        "                \"thumbnail\": {\n" +
        "                    \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\",\n" +
        "                    \"extension\": \"jpg\"\n" +
        "                },\n" +
        "                \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1017100\",\n" +
        "                \"comics\": {\n" +
        "                    \"available\": 4,\n" +
        "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\n" +
        "                    \"items\": [\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/47176\",\n" +
        "                            \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013) #1\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40632\",\n" +
        "                            \"name\": \"Hulk (2008) #53\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40630\",\n" +
        "                            \"name\": \"Hulk (2008) #54\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40628\",\n" +
        "                            \"name\": \"Hulk (2008) #55\"\n" +
        "                        }\n" +
        "                    ],\n" +
        "                    \"returned\": 4\n" +
        "                },\n" +
        "                \"series\": {\n" +
        "                    \"available\": 2,\n" +
        "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/series\",\n" +
        "                    \"items\": [\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/17765\",\n" +
        "                            \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013)\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/3374\",\n" +
        "                            \"name\": \"Hulk (2008 - 2012)\"\n" +
        "                        }\n" +
        "                    ],\n" +
        "                    \"returned\": 2\n" +
        "                },\n" +
        "                \"stories\": {\n" +
        "                    \"available\": 7,\n" +
        "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\n" +
        "                    \"items\": [\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92078\",\n" +
        "                            \"name\": \"Hulk (2008) #55\",\n" +
        "                            \"type\": \"cover\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92079\",\n" +
        "                            \"name\": \"Interior #92079\",\n" +
        "                            \"type\": \"interiorStory\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92082\",\n" +
        "                            \"name\": \"Hulk (2008) #54\",\n" +
        "                            \"type\": \"cover\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92083\",\n" +
        "                            \"name\": \"Interior #92083\",\n" +
        "                            \"type\": \"interiorStory\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92086\",\n" +
        "                            \"name\": \"Hulk (2008) #53\",\n" +
        "                            \"type\": \"cover\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92087\",\n" +
        "                            \"name\": \"Interior #92087\",\n" +
        "                            \"type\": \"interiorStory\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/105929\",\n" +
        "                            \"name\": \"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\n" +
        "                            \"type\": \"cover\"\n" +
        "                        }\n" +
        "                    ],\n" +
        "                    \"returned\": 7\n" +
        "                },\n" +
        "                \"events\": {\n" +
        "                    \"available\": 0,\n" +
        "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/events\",\n" +
        "                    \"items\": [],\n" +
        "                    \"returned\": 0\n" +
        "                },\n" +
        "                \"urls\": [\n" +
        "                    {\n" +
        "                        \"type\": \"detail\",\n" +
        "                        \"url\": \"http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=9eda5d952e9fb001de15ad03bcfdd1a8\"\n" +
        "                    },\n" +
        "                    {\n" +
        "                        \"type\": \"comiclink\",\n" +
        "                        \"url\": \"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=9eda5d952e9fb001de15ad03bcfdd1a8\"\n" +
        "                    }\n" +
        "                ]\n" +
        "            }\n" +
        "        ]\n" +
        "    }\n" +
        "}"
# How to run configurations for Elasticsearch

_All of the following commands can be run through `curl` or `Postman` with default `url` started with `http://localhost:9200/`._

1. Configurate analyzers for extracting keywords
- `PUT /ctsa_keywords`
- Using JSON content from [extracting-keyword-analyzer-configuration.json](https://github.com/anh-khue/ctsa-capstone/blob/elasticsearch/elasticsearch/extracting-keywords-analyzer-configuration.json)
2. Insert keywords
- `POST /ctsa_keywords/it_keywords/{id}`
- Insert values with JSON template from [insert-keyword.json](https://github.com/anh-khue/ctsa-capstone/blob/elasticsearch/elasticsearch/insert-keyword.json)
3. Search and Response
- `POST /ctsa_keywords/it_keywords/_search`
- In [search-and-response.json](https://github.com/anh-khue/ctsa-capstone/blob/elasticsearch/elasticsearch/search-and-response.json):
    - Using JSON template for `query` for sending request and search for keywords
    - Response will have the template like `response`

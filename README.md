# nowprj

docker build -t mytest .
docker run -v /Users/cesaremauri/Projects/extProjects/nowprj:/mnt -p 9090:9090 -w /mnt mytest ./scripts/build.sh
docker run -v /Users/cesaremauri/Projects/extProjects/nowprj:/mnt -p 9090:9090 -w /mnt mytest ./scripts/tests.sh
docker run -v /Users/cesaremauri/Projects/extProjects/nowprj:/mnt -p 9090:9090 -w /mnt mytest ./scripts/run.sh


curl --location 'localhost:9090/api/v1/order' \
--header 'Content-Type: application/json' \
--data '{"order": {"items": [{"product_id": 1,"quantity": 1 }, {"product_id": 2,"quantity": 5},{"product_id":3,"quantity":1}]}}'

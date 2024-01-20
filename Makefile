update:
	git submodule update --recursive --init

up-cluster:
	docker-compose -f docker/docker-compose.yml up --build -d

create-topic:
	curl 'http://localhost:9021/2.0/kafka/MkU3OEVBNTcwNTJENDM2Qg/topics?validate=false' \
      -X 'PUT' \
      -H 'Accept: */*' \
      -H 'Accept-Language: en-US,en;q=0.9,pt-BR;q=0.8,pt;q=0.7' \
      -H 'Connection: keep-alive' \
      -H 'Content-Type: application/json' \
      -H 'Cookie: _ga=GA1.1.865825557.1670966590; _ga_VTZK3GMMLR=GS1.1.1674635675.5.0.1674635675.0.0.0; _pk_id.1.1fff=09644ecf1bea016d.1675340151.; sidebar_collapsed=false; Idea-beb6ed88=05123f07-216c-45a0-bf2e-b1789f466766; Webstorm-9ce4fef8=f02870c8-ed73-4e13-8020-c4de447a0dfb; Studio-2a4584f8=657a5fda-522f-4eed-8e7f-b22336b71151; usedCommands=%5B%22cat%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%2Fcommand-runner-2023-08-11.log%22%2C%22ls%20-lah%22%2C%22ls%20-lah%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%22%2C%22docker%20ps%22%2C%22touch%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%2Fcommand-runner-2023-08-11.log%22%2C%22rm%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%2Fcommand-runner-2023-08-11.log%22%2C%22cd%20command-runner%20%26%26%20sudo%20-u%20eliasferreira%20git%20pull%20%26%26%20make%20deploy%22%2C%22cat%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%2Fcommand-runner-2023-08-10.log%22%2C%22cat%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%2Fcommand-runner-2023-08-09.log%22%2C%22pwd%22%2C%22docker%20stats%22%2C%22sudo%20-u%20eliasferreira%20git%20clone%20%22%2C%22ls%20-lah%20%2Fvar%2Flog%2Fproxy-server%22%2C%22cd%20project_name%20%26%26%20sudo%20-u%20eliasferreira%20git%20pull%20%26%26%20make%20deploy%22%2C%22cat%20%2Fvar%2Flog%2Fcommand-runner%2F2023-08%2Fcommand-runner-2023-08-07.log%22%5D' \
      -H 'Origin: http://localhost:9021' \
      -H 'Referer: http://localhost:9021/clusters/MkU3OEVBNTcwNTJENDM2Qg/management/create-topic' \
      -H 'Sec-Fetch-Dest: empty' \
      -H 'Sec-Fetch-Mode: cors' \
      -H 'Sec-Fetch-Site: same-origin' \
      -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36' \
      -H 'X-Requested-By: 290ec434-e1cd-4a94-8345-35ca0758660f' \
      -H 'X-Requested-With: undefined' \
      -H 'sec-ch-ua: "Not_A Brand";v="8", "Chromium";v="120", "Google Chrome";v="120"' \
      -H 'sec-ch-ua-mobile: ?0' \
      -H 'sec-ch-ua-platform: "Linux"' \
      --data-raw '{"name":"KAFKA_LEARNING","numPartitions":6,"replicationFactor":"1","configs":{"compression.type":"producer","leader.replication.throttled.replicas":"","remote.storage.enable":"false","message.downconversion.enable":"true","min.insync.replicas":"1","segment.jitter.ms":"0","local.retention.ms":"-2","cleanup.policy":"delete","flush.ms":"9223372036854775807","follower.replication.throttled.replicas":"","segment.bytes":"1073741824","retention.ms":"604800000","flush.messages":"9223372036854775807","message.format.version":"3.0-IV1","file.delete.delay.ms":"60000","max.compaction.lag.ms":"9223372036854775807","max.message.bytes":"1048588","min.compaction.lag.ms":"0","message.timestamp.type":"CreateTime","local.retention.bytes":"-2","preallocate":"false","min.cleanable.dirty.ratio":"0.5","index.interval.bytes":"4096","unclean.leader.election.enable":"false","delete.retention.ms":"86400000","retention.bytes":"-1","segment.ms":"604800000","message.timestamp.difference.max.ms":"9223372036854775807","segment.index.bytes":"10485760"}}' \
      --compressed

test:
	@./gradlew clean test

build:
	@./gradlew clean build

up-producer:
	@./gradlew producer:bootRun

up-consumer:
	@./gradlew consumer:bootRun

deploy-services:
	make build
	docker build \
      --file ./Dockerfile \
      --build-arg JAR_FILE=./app/consumer/build/libs/consumer-1.0.0.jar \
      --build-arg PORT=80 \
      --tag consumer-image:latest \
      .
	docker-compose up --build -d


#!/bin/bash

project_id=5491
stage_id=6277
scenario_id=6709

TIMESTAMP=$(echo $(($(date +%s%N)/1000000)))
URI="/api/v1/project/$project_id/stage/$stage_id/scenario/$scenario_id/deploy"

function makeSignature() {
	nl=$'\\n'

	ACCESSKEY="UUsAKQMp7LyZFZ2cEjwY"				# access key id (from portal or Sub Account)
	SECRETKEY="cxWzYOcVCqNFvpN9he0EWlNtvlY5a8f5au0u1PY9"				# secret key (from portal or Sub Account)

	METHOD="POST"

	SIG="$METHOD"' '"$URI"${nl}
	SIG+="$TIMESTAMP"${nl}
	SIG+="$ACCESSKEY"

	SIGNATURE=$(echo -n -e "$SIG"|iconv -t utf8 |openssl dgst -sha256 -hmac $SECRETKEY -binary|openssl enc -base64)
}

curl -i -X POST https://vpcsourcedeploy.apigw.ntruss.com$URI \
          -H "x-ncp-apigw-timestamp: $TIMESTAMP" \
          -H "x-ncp-iam-access-key: $ACCESSKEY" \
          -H "x-ncp-apigw-signature-v2: $SIGNATURE"


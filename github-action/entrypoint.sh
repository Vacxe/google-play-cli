#! /bin/bash

set -e

checkParameter() {
if [ -z "$2" ]
then
  echo "$1 is required, but not provided"
  exit 1
fi
}

SERVICE_ACCOUNT_JSON=${1}
TEMPLATE=${2}
CUSTOM_SCRIPT=${3}
PACKAGE_NAME=${4}
VERSION_CODE=${5}
TRACK=${6}
PATH_TO_APK=${7}
PATH_TO_BUNDLE=${8}
PATH_TO_MAPPING=${9}

FLAG_CHANGES_NOT_SENT_FOR_REVIEW=${10}
STATUS=${11}
USER_FRACTION=${12}

echo "Play console version:"
google-play-cli version

checkParameter "Service Account JSON" "$SERVICE_ACCOUNT_JSON"
export PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT="$SERVICE_ACCOUNT_JSON"

echo "Package name: $PACKAGE_NAME"
export APP_PACKAGE_NAME="$PACKAGE_NAME"

echo "Template: $TEMPLATE"

case $TEMPLATE in

  "custom-script")
    checkParameter "Custom script" "$CUSTOM_SCRIPT"
    eval "$CUSTOM_SCRIPT"
    ;;

  "apk-upload")
    checkParameter "Package name" "$PACKAGE_NAME"
    checkParameter "Version code" "$VERSION_CODE"
    checkParameter "Path to apk" "$PATH_TO_APK"

    sh /templates/apk-upload.sh "$PATH_TO_APK" "$VERSION_CODE" "$TRACK" "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW" "$STATUS" "$USER_FRACTION"
    ;;

  "bundles-upload")
    checkParameter "Package name" "$PACKAGE_NAME"
    checkParameter "Version code" "$VERSION_CODE"
    checkParameter "Path to bundle" "$PATH_TO_BUNDLE"

    sh /templates/bundles-upload.sh "$PATH_TO_BUNDLE" "$VERSION_CODE" "$TRACK" "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW" "$STATUS" "$USER_FRACTION"
    ;;

  "deobfuscation-files-upload")
    checkParameter "Package name" "$PACKAGE_NAME"
    checkParameter "Version code" "$VERSION_CODE"
    checkParameter "Path to mapping" "$PATH_TO_MAPPING"

    sh /templates/deobfuscation-files-upload.sh "$PATH_TO_MAPPING" "$VERSION_CODE" "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
    ;;

  *)
    echo -n "$TEMPLATE is unknown template, please check documentation for available templates"
    exit 1
    ;;

esac

exit 0

#! /bin/bash

set -e

function checkParameter() {
if [ -z "$2" ]
then
  echo "$1 is required, but not provided"
  exit 1
fi
}

ACTION=$1

CUSTOM_SCRIPT=$2

PACKAGE_NAME=$3
VERSION_CODE=$4
TRACK=$5

PATH_TO_APK=$6
PATH_TO_BUNDLE=$7
PATH_TO_MAPPING=$8

FLAG_CHANGES_NOT_SENT_FOR_REVIEW=$9

echo "Play console version:"
google-play-cli version

echo "Action performed: $ACTION"

case $ACTION in

  "custom-script")
    checkParameter "Custom script" "$CUSTOM_SCRIPT"
    $CUSTOM_SCRIPT
    ;;

  "apk-upload")
    checkParameter "Package name" "$PACKAGE_NAME"
    checkParameter "Version code" "$VERSION_CODE"
    checkParameter "Path to apk" "$PATH_TO_APK"

    .templates/apk-upload.sh "$PACKAGE_NAME" "$PATH_TO_APK" "$VERSION_CODE" "$TRACK" "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
    ;;

  "bundles-upload")
    checkParameter "Package name" "$PACKAGE_NAME"
    checkParameter "Version code" "$VERSION_CODE"
    checkParameter "Path to bundle" "$PATH_TO_BUNDLE"

    .templates/bundles-upload.sh "$PACKAGE_NAME" "$PATH_TO_BUNDLE" "$VERSION_CODE" "$TRACK" "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
    ;;

  "deobfuscation-files-upload")
    checkParameter "Package name" "$PACKAGE_NAME"
    checkParameter "Version code" "$VERSION_CODE"
    checkParameter "Path to mapping" "$PATH_TO_MAPPING"

    .templates/deobfuscation-files-upload.sh "$PACKAGE_NAME" "$PATH_TO_MAPPING" "$VERSION_CODE" "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
    ;;

  *)
    echo -n "$ACTION is unknown action, please check documentation for available actions"
    exit 1
    ;;

esac

exit 0

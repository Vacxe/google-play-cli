#! /bin/bash

set -e

SERVICE_ACCOUNT_JSON="${1}"
PACKAGE_NAME=$2
PATH_TO_APK=$3
VERSION_CODE=$4
TRACK=$5
FLAG_CHANGES_NOT_SENT_FOR_REVIEW=$5

echo "---"
echo "Package name: $PACKAGE_NAME"
echo "Path to apk: $PATH_TO_APK"
echo "Version code: $VERSION_CODE"
echo "Track: $TRACK"
echo "Changes not sent for review: $FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
echo "---"

export PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT="$SERVICE_ACCOUNT_JSON"
export APP_PACKAGE_NAME="$PACKAGE_NAME"

EDIT_ID=$(google-play-cli edit create)
echo "Edit id created: $EDIT_ID"
echo "Upload APK..."
google-play-cli apk upload --config-content "$SERVICE_ACCOUNT_JSON" --edit-id "$EDIT_ID" --apk "$PATH_TO_APK"
echo "Update track..."
google-play-cli tracks update --edit-id "$EDIT_ID" --track "$TRACK" --apk-version-code "$VERSION_CODE"
echo "Validate..."
google-play-cli edit validate --edit-id "$EDIT_ID"
echo "Commit..."
google-play-cli edit commit --edit-id "$EDIT_ID" --parameters "{\"changesNotSentForReview\": $FLAG_CHANGES_NOT_SENT_FOR_REVIEW}"

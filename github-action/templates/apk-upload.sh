#! /bin/bash

set -e

PACKAGE_NAME=$1
PATH_TO_APK=$2
VERSION_CODE=$3
TRACK=$4
FLAG_CHANGES_NOT_SENT_FOR_REVIEW=$5

echo "---"
echo "Package name: $PACKAGE_NAME"
echo "Path to apk: $PATH_TO_APK"
echo "Version code: $VERSION_CODE"
echo "Track: $TRACK"
echo "Changes not sent for review: $FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
echo "---"

EDIT_ID=$(google-play-cli edit create)
echo "Edit id created: $EDIT_ID"
echo "Upload APK..."
google-play-cli apk upload --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --apk "$PATH_TO_APK"
echo "Update track..."
google-play-cli tracks update --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --track "$TRACK" --apk-version-code "$VERSION_CODE"
echo "Validate..."
google-play-cli edit validate --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME"
echo "Commit..."
google-play-cli edit commit --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --parameters "{\"changesNotSentForReview\": $FLAG_CHANGES_NOT_SENT_FOR_REVIEW}"

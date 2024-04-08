#! /bin/bash

set -e

SERVICE_ACCOUNT_JSON="${1}"
PACKAGE_NAME="${2}"
PATH_TO_MAPPING="${3}"
VERSION_CODE="${4}"
FLAG_CHANGES_NOT_SENT_FOR_REVIEW="${5}"

echo "---"
echo "Package name: $PACKAGE_NAME"
echo "Path to mapping: $PATH_TO_MAPPING"
echo "Version code: $VERSION_CODE"
echo "Changes not sent for review: $FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
echo "---"

export PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT="$SERVICE_ACCOUNT_JSON"
export APP_PACKAGE_NAME="$PACKAGE_NAME"

EDIT_ID=$(google-play-cli edit create)
echo "Edit id created: $EDIT_ID"
echo "Upload deobfuscation files..."
google-play-cli deobfuscation-files upload --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --deobfuscation "$PATH_TO_MAPPING" --apk-version-code "$VERSION_CODE"
echo "Validate..."
google-play-cli edit validate --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME"
echo "Commit..."
google-play-cli edit commit --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --parameters "{\"changesNotSentForReview\": $FLAG_CHANGES_NOT_SENT_FOR_REVIEW}"

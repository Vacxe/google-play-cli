#! /bin/bash

set -e

PATH_TO_MAPPING="${3}"
VERSION_CODE="${4}"
FLAG_CHANGES_NOT_SENT_FOR_REVIEW="${5}"

echo "---"
echo "Path to mapping: $PATH_TO_MAPPING"
echo "Version code: $VERSION_CODE"
echo "Changes not sent for review: $FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
echo "---"

EDIT_ID=$(google-play-cli edit create)
echo "Edit id created: $EDIT_ID"
echo "Upload deobfuscation files..."
google-play-cli deobfuscation-files upload --edit-id "$EDIT_ID" --deobfuscation "$PATH_TO_MAPPING" --apk-version-code "$VERSION_CODE"
echo "Validate..."
google-play-cli edit validate --edit-id "$EDIT_ID" || true # Ignore until changes-not-sent-for-review will be added as parameter
echo "Commit..."
google-play-cli edit commit --edit-id "$EDIT_ID" \
  ${FLAG_CHANGES_NOT_SENT_FOR_REVIEW:+ --changes-not-sent-for-review "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"}

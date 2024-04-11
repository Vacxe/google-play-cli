#! /bin/bash

set -e

PATH_TO_APK=$3
VERSION_CODE=$4
TRACK=$5
FLAG_CHANGES_NOT_SENT_FOR_REVIEW=$6
STATUS="${7}"
USER_FRACTION=${8}

echo "---"
echo "Path to apk: $PATH_TO_APK"
echo "Version code: $VERSION_CODE"
echo "Track: $TRACK"
echo "Status: $STATUS"
echo "User fraction: $USER_FRACTION"
echo "Changes not sent for review: $FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
echo "---"

EDIT_ID=$(google-play-cli edit create)
echo "Edit id created: $EDIT_ID"
echo "Upload APK..."
google-play-cli apk upload --edit-id "$EDIT_ID" --apk "$PATH_TO_APK"
echo "Update track..."
google-play-cli tracks update --edit-id "$EDIT_ID" --version-code "$VERSION_CODE" --track "$TRACK" \
  ${USER_FRACTION:+ --user-fraction "$USER_FRACTION"} \
  ${STATUS:+ --status "$STATUS"}
echo "Validate..."
google-play-cli edit validate --edit-id "$EDIT_ID" || true # Ignore until changes-not-sent-for-review will be added as parameter
echo "Commit..."
google-play-cli edit commit --edit-id "$EDIT_ID" \
  ${FLAG_CHANGES_NOT_SENT_FOR_REVIEW:+ --changes-not-sent-for-review "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"}

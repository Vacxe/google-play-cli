#! /bin/bash

set -e

SERVICE_ACCOUNT_JSON="${1}"
PACKAGE_NAME="${2}"
PATH_TO_BUNDLE="${3}"
VERSION_CODE="${4}"
TRACK="${5}"
FLAG_CHANGES_NOT_SENT_FOR_REVIEW="${6}"
STATUS="${7}"
USER_FRACTION=${8}

export PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT="$SERVICE_ACCOUNT_JSON"
export APP_PACKAGE_NAME="$PACKAGE_NAME"

echo "---"
echo "Package name: $PACKAGE_NAME"
echo "Path to bundle: $PATH_TO_BUNDLE"
echo "Version code: $VERSION_CODE"
echo "Track: $TRACK"
echo "Status: $STATUS"
echo "User fraction: $USER_FRACTION"
echo "Changes not sent for review: $FLAG_CHANGES_NOT_SENT_FOR_REVIEW"
echo "---"

EDIT_ID=$(google-play-cli edit create)
echo "Edit id created: $EDIT_ID"
echo "Upload Bundle..."
google-play-cli bundles upload --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --bundle "$PATH_TO_BUNDLE"
echo "Update track..."
google-play-cli tracks update --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" --apk-version-code "$VERSION_CODE" --track "$TRACK" \
  ${USER_FRACTION:+ --user-fraction "$USER_FRACTION"} \
  ${STATUS:+ --status "$STATUS"}
echo "Validate..."
google-play-cli edit validate --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME"
echo "Commit..."
google-play-cli edit commit --edit-id "$EDIT_ID" --package-name "$PACKAGE_NAME" \
  ${FLAG_CHANGES_NOT_SENT_FOR_REVIEW:+ --changes-not-sent-for-review "$FLAG_CHANGES_NOT_SENT_FOR_REVIEW"}

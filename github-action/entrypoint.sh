#! /bin/bash

set -e

path_to_apk=$1
version_code=$2
path_to_mapping=$3
track=$4
changesNotSentForReview=$5

echo "Play console version:"
google-play-cli version

edit_id=$(google-play-cli edit create)
echo "Edit id created: $edit_id"
echo "Upload APK..."
google-play-cli apk upload --edit-id $edit_id --apk "$path_to_apk"
echo "Update track..."
google-play-cli tracks update --edit-id $edit_id --track $track --apk-version-code $version_code
echo "Upload deobfuscation files..."
google-play-cli deobfuscation-files upload --edit-id $edit_id --deobfuscation "$path_to_mapping" --apk-version-code $apk_version_code
echo "Validate..."
google-play-cli edit validate --edit-id $edit_id
echo "Commit..."
google-play-cli edit commit --edit-id $edit_id --parameters "{\"changesNotSentForReview\": $changesNotSentForReview}"

exit 0


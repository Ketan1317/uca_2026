#!/bin/bash

# Check if commit message is provided
if [ $# -eq 0 ]; then
    echo "Usage: ./gitpush.sh \"Commit message\""
    exit 1
fi

git add .

git commit -m "$1"

# Push only if commit succeeded
if [ $? -eq 0 ]; then
    git push
fi

#!/bin/bash

set -e

if [ -z "$1" ]; then
  echo "Usage: $0 <number_of_days>"
  exit 1
fi

NUM_DAYS=$1

if [ "$NUM_DAYS" -lt 1 ]; then
  echo "Number of days must be >= 1"
  exit 1
fi

for ((i=1; i<=NUM_DAYS; i++))
do
  DIR="Day${i}"

  mkdir -p "${DIR}"

  # Create .gitkeep in root of each Day folder
  touch "${DIR}/.gitkeep"

  # Also ensure Solutions exists (optional but useful for your workflow)
  mkdir -p "${DIR}/Solutions"
  touch "${DIR}/Solutions/.gitkeep"
done

git add .

echo "Done. Folders initialized."
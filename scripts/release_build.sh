#!/usr/bin/env bash
set -euo pipefail

./scripts/preflight_release.sh

gradle clean test lint assembleRelease --no-daemon

echo "Release build completed: app/build/outputs/apk/release/"

# Deployment Checklist

Use this checklist before publishing a release build.

## Build Environment
- [ ] JDK 17 is active (`java -version`).
- [ ] Android SDK platform/tools are installed for API 34.
- [ ] Gradle dependencies resolve successfully.
- [ ] `./scripts/preflight_release.sh` passes locally.

## Quality Gates
- [ ] CI workflow (`.github/workflows/android-ci.yml`) passes on the release commit.
- [ ] Unit tests pass.
- [ ] Instrumentation tests pass on at least one API level.
- [ ] Lint passes with no release-blocking issues.

## App Behavior
- [ ] Coordinate search validates range (lat: -90..90, lon: -180..180).
- [ ] API error state shows retry and app recovers correctly.
- [ ] Forecast renders without crashes for partial API data.

## Release Build
- [ ] `release` build type is minified and resources are shrunk.
- [ ] `keystore.properties` exists locally (from `keystore.properties.example`) with real signing credentials.
- [ ] Version code/version name are incremented.
- [ ] `./scripts/release_build.sh` succeeds locally.
- [ ] Manual CI release workflow (`.github/workflows/android-release.yml`) completes and uploads artifact.

## Rollout
- [ ] Internal testing track upload successful.
- [ ] Crash-free and ANR metrics monitored for first rollout window.

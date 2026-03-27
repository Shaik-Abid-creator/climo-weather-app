# Codebase Issue Review: Proposed Tasks

## 1) Typo Fix Task
**Title:** Fix stale project folder name in setup docs (`climo-android` → actual repo folder)

**Issue found:** Multiple docs tell users to run `cd climo-android`, but this repository is named `climo-weather-app`, which can cause immediate onboarding friction.

**Where observed:**
- `README.md`
- `QUICKSTART.md`
- `PROJECT_SUMMARY.md`

**Proposed task:**
- Replace stale folder references with a repo-agnostic instruction (for example, "cd <project-root>") or update to the current repository folder name.
- Verify all copy-pasted build/run blocks are internally consistent.

**Acceptance criteria:**
- No documentation instructs users to `cd` into a non-existent folder.
- Quick start steps work when executed from the checked-out repository root.

---

## 2) Bug Fix Task
**Title:** Make daily forecast mapping resilient to uneven API arrays in `toDailyWeatherList()`

**Issue found:** `toDailyWeatherList()` iterates over `time.indices` and directly indexes all other daily arrays using the same index. If one array is shorter (API partial/malformed response), the app can crash with `IndexOutOfBoundsException`.

**Where observed:**
- `app/src/main/java/com/climo/weather/model/WeatherModels.kt`

**Proposed task:**
- Compute a safe minimum size across all required daily arrays.
- Map only over valid indices (or zip arrays safely).
- Optionally log or surface when truncation happened for diagnostics.

**Acceptance criteria:**
- No out-of-bounds crash when any daily field list is shorter than `time`.
- UI still renders available forecast rows from the shared safe range.

---

## 3) Comment/Documentation Discrepancy Task
**Title:** Align API parameter examples with actual Open-Meteo fields used in code

**Issue found:** Quickstart API reference uses `current=temperature,humidity,...`, while code uses `relative_humidity` and additional fields like `apparent_temperature`, `is_day`, `precipitation`, and `wind_direction_10m`.

**Where observed:**
- Docs: `QUICKSTART.md`
- Implementation: `app/src/main/java/com/climo/weather/network/WeatherService.kt`

**Proposed task:**
- Update docs to match actual query parameters sent by `WeatherService` defaults.
- Add one short note that these fields are chosen to match the current UI cards.

**Acceptance criteria:**
- Documentation API examples reflect implementation defaults exactly.
- No mismatch between documented fields and parsed model fields.

---

## 4) Test Improvement Task
**Title:** Add unit tests for weather-code mappings and forecast transformation edge cases

**Issue found:** There are currently no visible tests covering mapping helpers or daily list transformation logic.

**Where observed:**
- Mapping logic in `WeatherModels.kt`
- No corresponding test source files under `app/src/test`

**Proposed task:**
- Add unit tests for:
  - `getWeatherDescription()` known codes + unknown fallback.
  - `getWeatherIcon()` known codes + unknown fallback.
  - `toDailyWeatherList()` with fully valid arrays and uneven-array edge cases.

**Acceptance criteria:**
- Tests fail before bug fix (uneven arrays) and pass after fix.
- Critical mapping behavior is regression-protected.

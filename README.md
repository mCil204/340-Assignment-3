# Limbus Company Character API

A RESTful CRUD API for Limbus Company characters.


## Character Attributes

| Attribute | Type | Rules |
|---|---|---|
| characterId | long | Unique, auto-generated |
| name | String | Required (not null, not blank) |
| description | String | Required (not null, not blank) |
| specialty | String | Optional |
| faction | String | Optional (used for filtering) |
| imageUrl | String | Optional |

## Endpoints

| Method | Path | Description | Success Status |
|---|---|---|---|
| GET | /api/characters | Get all characters | 200 |
| GET | /api/characters/{id} | Get one character by ID | 200 |
| POST | /api/characters | Add a character | 201 |
| PUT | /api/characters/{id} | Update a character | 200 |
| DELETE | /api/characters/{id} | Delete a character | 204 |
| GET | /api/characters?faction=LCB | Filter by faction (case-insensitive) | 200 |
| GET | /api/characters?name=sang | Search by name (partial, case-insensitive) | 200 |

## Error Responses

| Status | When |
|---|---|
| 404 | The requested ID does not exist |
| 400 | A POST or PUT is missing `name` or `description` |

## Example Request Body (POST and PUT)

```json
{
  "name": "Yi Sang",
  "description": "Designated Sinner #1 of Limbus Company's LCB department. Researcher and Architect.",
  "specialty": "Sinking",
  "faction": "LCB",
  "imageUrl": "https://example.com/yi-sang.png"
}
```

## Deployed API

Base URL: https://three40-3-character-api.onrender.com/api/characters

**Cold-start note:** This API runs on Render's free tier. The first request after a quiet period can take up to a minute to respond.
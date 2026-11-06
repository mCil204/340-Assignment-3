# Limbus Company Character API

A web app for Limbus Company characters from assignment 2 and 3.


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
  "imageUrl": "/images/yi-sang.jpg"
}
```

| Route | Description |
|---|---|
| GET / | Gallery of all characters. Search by name and filter by faction with `?name=` and `?faction=` (both can be combined) |
| GET /characters/{id} | Details page for one character, with Edit and Delete buttons |
| GET /characters/new | Form to add a character |
| POST /characters | Saves a new character, then redirects to the gallery with a message |
| GET /characters/{id}/edit | The same form, pre-filled, to edit a character |
| POST /characters/{id} | Saves the edit, then redirects to the details page with a message |
| POST /characters/{id}/delete | Deletes the character (after a confirmation prompt), then redirects to the gallery with a message |
| GET /about | About page |


## Live Site

Base URL: https://three40-3-character-api.onrender.com

**Cold-start note:** This API runs on Render's free tier. The first request after a quiet period can take up to a minute to respond.
<#import "layout.ftlh" as l>
<@l.page title=character.name mainClass="character-page">
    <section class="character-details">
        <div class="character-image-container">
            <img src="${(character.imageUrl?has_content)?then(character.imageUrl, '/images/placeholder.svg')}"
                 alt="Portrait of ${character.name}" class="large-character-image">
        </div>

        <h1>${character.name}</h1>
        <p><strong>Specialty:</strong>
            <span class="specialty ${(character.specialty!'')?lower_case}">${character.specialty!'None'}</span>
        </p>
        <p><strong>Faction:</strong> ${character.faction!'None'}</p>
        <h2>Description</h2>
        <p>${character.description}</p>

        <a class="button" href="/characters/${character.characterId?c}/edit">Edit</a>
        <form method="post" action="/characters/${character.characterId?c}/delete"
              style="display:inline"
              onsubmit="return confirm('Delete ${character.name?js_string}?');">
            <button type="submit" class="button">Delete</button>
        </form>
    </section>

    <a class="button" href="/">Back to Gallery</a>
</@l.page>
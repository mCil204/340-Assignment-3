<#import "layout.ftlh" as l>
<#assign factions = ["N. Corp", "LCB", "The Middle", "Liu Association", "Molar Boatworks"]>
<@l.page title="Gallery" active="home">
    <section class="intro">
        <h1>Limbus Company Character Gallery</h1>
        <p>From Project Moon's Limbus Company, a single-player turn-based RPG</p>
    </section>

    <section class="character-search">
        <h2>Find a Character</h2>
        <form method="get" action="/" class="search-controls">
            <input type="search" name="name" value="${name!}"
                   placeholder="Search characters by name..." aria-label="Search characters">
                    <select name="faction" aria-label="Filter by faction">
                        <option value="">All factions</option>
                        <#list factions as f>
                            <option value="${f}" <#if (faction!'')==f>selected</#if>>${f}</option>
                        </#list>
                    </select>
            <button type="submit">Search</button>
            <a href="/">Clear</a>
        </form>
    </section>

    <section class="gallery" aria-label="Character gallery">
        <#list characters as c>
            <article class="card">
                <img src="${(c.imageUrl?has_content)?then(c.imageUrl, '/images/placeholder.svg')}"
                     alt="Picture of ${c.name}">
                <h2>${c.name}</h2>
                <p>
                    <span class="specialty ${(c.specialty!'')?lower_case}">${c.specialty!'No specialty'}</span>
                     ${c.faction!'No faction'}
                </p>
                <p>${(c.description!'')?truncate(160, '...')}</p>
                <a class="button" href="/characters/${c.characterId?c}">Details</a>
            </article>
        <#else>
            <p class="no-results">No characters found.</p>
        </#list>
    </section>
</@l.page>
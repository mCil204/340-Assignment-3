<#macro page title active="" mainClass="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/styles.css">
    <title>${title} | Limbus Company Characters</title>
</head>
<body>
    <header class="top-bar">
        <nav class="navigation">
            <a href="/" class="${(active == 'home')?then('active', '')}">Home</a>
            <a href="/about" class="${(active == 'about')?then('active', '')}">About</a>
            <a href="/characters/new" class="${(active == 'new')?then('active', '')}">Add a Character</a>
        </nav>
    </header>

    <main class="${mainClass}">
        <#if message??>
            <p class="alert">${message}</p>
        </#if>
        <#nested>
    </main>

    <footer>
        <p>&copy; 2026 Character Gallery for Educational Purposes</p>
    </footer>
</body>
</html>
</#macro>
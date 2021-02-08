# Contributing

For å bidra med kode, følger vi følgende prosedyre:

### Første steg
Lag en ny brnach basert på master branchen.
```sh
$ git checkout -b <navn> origin/master
```

Nå kan du gjøre arbeidet ditt.

### Klar for commit og push

Når du er klar, kan du lage en ny commit.  
Først legg til eventuelle nye filer, følgende kommando legger til alle filer.
```sh
$ git add .
```

Så lager du en commit:

```sh
$ git commit -m "melding"
```

Før du pusher, hent inn nye endringer:

```sh
$ git pull origin master
```

Løs eventuelle merge conflicts i intellij.

Så kan du pushe dine endringer, der navnet er navnet på branchen du lagde tidligere.
```sh
$ git push origin master
```

### Pull request

Når den nye branchen med endringene er pushet, lager du en ny pull request.  
Denne merge din branch, inn i master branchen.  
![lage pr](https://i.imgur.com/0UnA23s.png)

Før en PR kan merges, skal den godkjennes av minst én annen person.

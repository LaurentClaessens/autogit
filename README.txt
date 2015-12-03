This program loops over the directory given in argument and let you know what directory need a 'git commit'. It also propose some usual maintenance actions :
* edit .gitignore
* git commit -a
* git diff

Actions like 'git diff' have to be run in an external terminal (because we want to respect the choice you made in your ".gitconfig"). In order to select your preferred terminal, edit the "autogit.cfg" file.

If you are able to install/use Jgit under Ubuntu, explain me and I would be glad to remove my crappy code inside 'GitRepository.java'.

Next improvements :

* help to update .gitignore (proposing to add untracked files)
* help making 'git add *'

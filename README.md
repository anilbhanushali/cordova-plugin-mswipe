MSwipe Card Reader Plugin
======

0. Clone the repo:

```bash
git clone git@github.com:anilbhanushali/cordova-plugin-mswipe.git
cd cordova-plugin-mswipe
```

1. Create your app

In a different terminal, create your app:

Run `ionic start` if using Ionic, or `cordova create` if not.

We will be testing an developing our plugin from this app. Plugins are always built and developed from within an existing Cordova project.

2. Add platforms

`cordova platform add android`

3. Install and Link your Plugin

```bash
cordova plugin add --link ~/path/to/plugin
```

With the `--link` flag, Cordova creates a symbolic link to our plugin so we can update the plugin locally and rebuild without having to copy anything.

Note: to remove the plugin, use the symbolic name of the plugin instead:

```bash
cordova plugin rm cordova-plugin-mswipe
```

4. Testing process

When our plugin is linked, we can make modifications to the native code in our plugin and rebuild our app immediately. If we make modifications to the JavaScript portion of our plugin, we need to reinstall the plugin (as far as I know, I am trying to find a way around this).

```bash
cordova plugin rm cordova-plugin-mswipe
cordova plugin add --link ~/path/to/plugin
```


Thanks to https://gist.github.com/mlynch/c9a469948680979a8740d68c47d1cf98 for putting out a plugin template.
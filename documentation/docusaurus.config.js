/** @type {import('@docusaurus/types').DocusaurusConfig} */
module.exports = {
  title: '️Kleuth',
  tagline: 'Kotlin + sleuth🕵️‍♂️ . A lightweight, flexible framework for dynamically building Spring REST APIs.',
  url: 'https://github.com/alien-head',
  baseUrl: '/klueth/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',
  organizationName: 'alienhead', // Usually your GitHub org/user name.
  projectName: 'kleuth', // Usually your repo name.
  themeConfig: {
    navbar: {
      title: 'Kleuth',
      logo: {
        alt: 'My Site Logo',
        src: 'img/logo.svg',
      },
      items: [
        {
          to: 'docs/',
          activeBasePath: 'docs',
          label: 'Docs',
          position: 'left',
        },
//        {to: 'blog', label: 'Blog', position: 'left'},
        {
          href: 'https://github.com/alien-head/kleuth',
          label: 'GitHub',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Docs',
          items: [
            {
              label: 'Getting Started',
              to: 'docs/',
            },
            {
              label: 'Framework',
              to: 'docs/framework/structure',
            },
            {
              label: 'Contributing',
              to: 'docs/contributing',
            },
          ],
        },
//        {
//          title: 'Community',
//          items: [
//            {
//              label: 'Stack Overflow',
//              href: 'https://stackoverflow.com/questions/tagged/docusaurus',
//            },
//            {
//              label: 'Discord',
//              href: 'https://discordapp.com/invite/docusaurus',
//            },
//            {
//              label: 'Twitter',
//              href: 'https://twitter.com/docusaurus',
//            },
//          ],
//        },
        {
          title: 'More',
          items: [
//            {
//              label: 'Blog',
//              to: 'blog',
//            },
            {
              label: 'GitHub',
              href: 'https://github.com/alien-head/kleuth',
            },
          ],
        },
      ],
      copyright: `Kleuth is created by <a href='https://github.com/cjpoulsen'>@cpoulsen</a>. Built with Docusaurus.`,
    },
    prism: {
      additionalLanguages: ['kotlin'],
      theme: require('prism-react-renderer/themes/dracula')
    },
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          editUrl:
            'https://github.com/alienhead/kleuth/edit/master/documentation/',
        },
//        blog: {
//          showReadingTime: true,
//          // Please change this to your repo.
//          editUrl:
//            'https://github.com/alienhead/kleuth/edit/master/documentation/blog/',
//        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
};

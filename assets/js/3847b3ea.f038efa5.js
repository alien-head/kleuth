(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{135:function(e,t,n){"use strict";n.r(t),t.default=n.p+"assets/images/basic_structure-926f3f480901b408a29748c8ab2cb4f0.png"},74:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return i})),n.d(t,"metadata",(function(){return l})),n.d(t,"toc",(function(){return p})),n.d(t,"default",(function(){return u}));var r=n(3),a=n(7),o=(n(0),n(88)),i={title:"Setup",slug:"/"},l={unversionedId:"setup",id:"setup",isDocsHomePage:!1,title:"Setup",description:"Add the Klueth dependency to your project",source:"@site/docs/setup.md",slug:"/",permalink:"/kleuth/docs/",editUrl:"https://github.com/alienhead/kleuth/edit/master/documentation/docs/setup.md",version:"current",sidebar:"docs",next:{title:"Structuring the API",permalink:"/kleuth/docs/framework/structure"}},p=[{value:"Add the Klueth dependency to your project",id:"add-the-klueth-dependency-to-your-project",children:[{value:"Gradle:",id:"gradle",children:[]}]},{value:"Enable Kleuth on the Application class",id:"enable-kleuth-on-the-application-class",children:[]},{value:"Set the necessary application properties",id:"set-the-necessary-application-properties",children:[{value:"Example",id:"example",children:[]}]},{value:"That&#39;s it!",id:"thats-it",children:[]}],c={toc:p};function u(e){var t=e.components,i=Object(a.a)(e,["components"]);return Object(o.b)("wrapper",Object(r.a)({},c,i,{components:t,mdxType:"MDXLayout"}),Object(o.b)("h2",{id:"add-the-klueth-dependency-to-your-project"},"Add the Klueth dependency to your project"),Object(o.b)("h3",{id:"gradle"},"Gradle:"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="build.gradle.kts"',title:'"build.gradle.kts"'},'implementation("io.alienhead:kleuth")\n')),Object(o.b)("h2",{id:"enable-kleuth-on-the-application-class"},"Enable Kleuth on the Application class"),Object(o.b)("p",null,"Add the Kleuth Autoconfiguration annotation ",Object(o.b)("inlineCode",{parentName:"p"},"@EnableKleuth")," to the main ",Object(o.b)("inlineCode",{parentName:"p"},"Application")," class."),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="Application.kt"',title:'"Application.kt"'},"@EnableKleuth\n@SpringBootApplication\nclass Application\n\nfun main(args: Array<String>) {\n  runApplication<Application>(*args)\n}\n")),Object(o.b)("h2",{id:"set-the-necessary-application-properties"},"Set the necessary application properties"),Object(o.b)("p",null,"Kleuth needs to know the fully qualified path to the root of the REST API.\nThis is set with the ",Object(o.b)("inlineCode",{parentName:"p"},"kleuth.core.pathToRoot")," property."),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="application.properties"',title:'"application.properties"'},"kleuth.core.pathToRoot=PATH_TO_ROOT_OF_API\n")),Object(o.b)("h3",{id:"example"},"Example"),Object(o.b)("p",null,"With the following directory structure:"),Object(o.b)("p",null,Object(o.b)("img",{alt:"Kleuth Structure",src:n(135).default})),Object(o.b)("p",null,"The user expects a ",Object(o.b)("inlineCode",{parentName:"p"},"GET")," ",Object(o.b)("inlineCode",{parentName:"p"},"/pizzas")," endpoint off the root of the REST API."),Object(o.b)("p",null,"Therefore, ",Object(o.b)("inlineCode",{parentName:"p"},"kleuth.core.pathToRoot")," should be set like this:"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="application.properties"',title:'"application.properties"'},"kleuth.core.pathToRoot=io/alienhead/pizza_restaurant/api/rest\n")),Object(o.b)("p",null,"If running locally, the ",Object(o.b)("inlineCode",{parentName:"p"},"GET")," pizzas endpoint would be served at ",Object(o.b)("inlineCode",{parentName:"p"},"localhost:8080/pizzas"),"."),Object(o.b)("h3",{id:""}),Object(o.b)("p",null,"If the ",Object(o.b)("inlineCode",{parentName:"p"},"pathToRoot")," property were to be set as follows:"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="application.properties"',title:'"application.properties"'},"kleuth.core.pathToRoot=io/alienhead/pizza_restaurant/api/rest/pizzas\n")),Object(o.b)("p",null,"The ",Object(o.b)("inlineCode",{parentName:"p"},"GET")," pizzas endpoint would be served at ",Object(o.b)("inlineCode",{parentName:"p"},"localhost:8080")," and the ",Object(o.b)("inlineCode",{parentName:"p"},"orders")," package would be ignored."),Object(o.b)("h2",{id:"thats-it"},"That's it!"),Object(o.b)("p",null,"With ",Object(o.b)("inlineCode",{parentName:"p"},"@EnableKleuth")," and the ",Object(o.b)("inlineCode",{parentName:"p"},"pathToRoot")," property set, you are ready to dynamically build your Spring REST API with Kleuth!"))}u.isMDXComponent=!0},88:function(e,t,n){"use strict";n.d(t,"a",(function(){return s})),n.d(t,"b",(function(){return h}));var r=n(0),a=n.n(r);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function l(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function p(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var c=a.a.createContext({}),u=function(e){var t=a.a.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):l(l({},t),e)),n},s=function(e){var t=u(e.components);return a.a.createElement(c.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return a.a.createElement(a.a.Fragment,{},t)}},d=a.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,o=e.originalType,i=e.parentName,c=p(e,["components","mdxType","originalType","parentName"]),s=u(n),d=r,h=s["".concat(i,".").concat(d)]||s[d]||b[d]||o;return n?a.a.createElement(h,l(l({ref:t},c),{},{components:n})):a.a.createElement(h,l({ref:t},c))}));function h(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var o=n.length,i=new Array(o);i[0]=d;var l={};for(var p in t)hasOwnProperty.call(t,p)&&(l[p]=t[p]);l.originalType=e,l.mdxType="string"==typeof e?e:r,i[1]=l;for(var c=2;c<o;c++)i[c]=n[c];return a.a.createElement.apply(null,i)}return a.a.createElement.apply(null,n)}d.displayName="MDXCreateElement"}}]);
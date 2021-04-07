(window.webpackJsonp=window.webpackJsonp||[]).push([[17],{84:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return c})),n.d(t,"metadata",(function(){return i})),n.d(t,"toc",(function(){return p})),n.d(t,"default",(function(){return s}));var r=n(3),o=n(7),a=(n(0),n(88)),c={title:"Content Type",slug:"/framework/content-type"},i={unversionedId:"content-type",id:"content-type",isDocsHomePage:!1,title:"Content Type",description:"Kleuth defaults the produces and consumes attributes of a request mapping to JSON. If you need to override this behavior, say, to consume a CSV file,",source:"@site/docs/content-type.md",slug:"/framework/content-type",permalink:"/kleuth/docs/framework/content-type",editUrl:"https://github.com/alienhead/kleuth/edit/master/documentation/docs/content-type.md",version:"current",sidebar:"docs",previous:{title:"Path Variables",permalink:"/kleuth/docs/framework/path-variables"},next:{title:"Response Entity Shortcuts",permalink:"/kleuth/docs/framework/response-entity-shortcuts"}},p=[],u={toc:p};function s(e){var t=e.components,n=Object(o.a)(e,["components"]);return Object(a.b)("wrapper",Object(r.a)({},u,n,{components:t,mdxType:"MDXLayout"}),Object(a.b)("p",null,"Kleuth defaults the ",Object(a.b)("inlineCode",{parentName:"p"},"produces")," and ",Object(a.b)("inlineCode",{parentName:"p"},"consumes")," attributes of a request mapping to JSON. If you need to override this behavior, say, to consume a CSV file,\nthe request method annotations (",Object(a.b)("inlineCode",{parentName:"p"},"Get"),", ",Object(a.b)("inlineCode",{parentName:"p"},"Post"),", etc.) provide the option to set custom ",Object(a.b)("inlineCode",{parentName:"p"},"produces")," and ",Object(a.b)("inlineCode",{parentName:"p"},"consumes")," attributes."),Object(a.b)("p",null,"This is just like what needs to be done on a Spring ",Object(a.b)("inlineCode",{parentName:"p"},"RequestMapping")," function."),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},'@Post(consumes = "text/csv")\nfun postCSV( /* ... */ ) {\n    // ...\n}\n')))}s.isMDXComponent=!0},88:function(e,t,n){"use strict";n.d(t,"a",(function(){return l})),n.d(t,"b",(function(){return m}));var r=n(0),o=n.n(r);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function c(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?c(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):c(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function p(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var u=o.a.createContext({}),s=function(e){var t=o.a.useContext(u),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},l=function(e){var t=s(e.components);return o.a.createElement(u.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},f=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,a=e.originalType,c=e.parentName,u=p(e,["components","mdxType","originalType","parentName"]),l=s(n),f=r,m=l["".concat(c,".").concat(f)]||l[f]||d[f]||a;return n?o.a.createElement(m,i(i({ref:t},u),{},{components:n})):o.a.createElement(m,i({ref:t},u))}));function m(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var a=n.length,c=new Array(a);c[0]=f;var i={};for(var p in t)hasOwnProperty.call(t,p)&&(i[p]=t[p]);i.originalType=e,i.mdxType="string"==typeof e?e:r,c[1]=i;for(var u=2;u<a;u++)c[u]=n[u];return o.a.createElement.apply(null,c)}return o.a.createElement.apply(null,n)}f.displayName="MDXCreateElement"}}]);
$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback21("function Qyc(){}\nfunction Syc(){}\nfunction Uyc(){}\nfunction kgd(){VYb.call(this)}\nfunction V_b(a,b){return UN(a.H.Lm(b),2)}\nfunction W_b(a){return a.e.q+'themes/'+a.P.b}\nfunction Vwd(){xYb.call(this);this.I=a6d;this.b=new GHd}\nfunction yKc(c,a){var b=c;a.notifyChildrenOfSizeChange=kLd(function(){b.Pk()})}\nfunction GKc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction vKc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction uKc(a,b){var c,d;for(c=DEd(ZCd(a.g));c.b.nh();){d=UN(IEd(c),2);if($N(a.g.Lm(d))===$N(b)){return d}}return null}\nfunction zKc(a,b){var c,d;d=uKc(a,b);d!=null&&a.g.Om(d);c=UN(a.b.Lm(b),254);if(c){a.b.Om(b);return Yc(a,c)}else if(b){return Yc(a,b)}return false}\nfunction wKc(a){var b,c,d;d=(otb(),a.ac).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];mtb.Qg(c,hQd)}}\nfunction AKc(a,b){var c,d,e;if((iu(),b).hasAttribute(z0d)){e=ou(b,z0d);a.f.Nm(e,b);Vt(b,'')}else{d=(otb(),Bvb(b));for(c=0;c<d;c++){AKc(a,Avb(b,c))}}}\nfunction BKc(a,b,c){var d,e;if(!b){return}d=VN(a.f.Lm(c));if(!d&&a.e){throw new QAd('No location '+c+nUd)}e=UN(a.g.Lm(c),18);if(e==b){return}!!e&&zKc(a,e);a.e||(d=(otb(),a.ac));Oc(a,b,(otb(),d));a.g.Nm(c,b)}\nfunction CKc(a,b){var c,d,e;d=b.ad();e=UN(a.b.Lm(d),254);if(Y8b(b.Od())){if(!e){c=uKc(a,d);Yc(a,d);e=new c9b(b,a.c);Nc(a,e,VN(a.f.Lm(c)));a.b.Nm(d,e)}T8b(e.b)}else{if(e){c=uKc(a,d);Yc(a,e);Nc(a,d,VN(a.f.Lm(c)));a.b.Om(d)}}}\nfunction Myc(c){var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.ik(Xjb,Y5d,d);var d={setter:function(a,b){a.d=b},getter:function(a){return a.d}};c.ik(Xjb,Z5d,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.ik(Xjb,$5d,d)}\nfunction DKc(){var a;Zc.call(this);this.f=new GHd;this.g=new GHd;this.b=new GHd;this.j='';this.e=false;ib(this,(otb(),_v($doc)));a=this.ac.style;Yx(a,qSd,(dy(),vLd));a[kUd]=0+(PB(),MLd);a[DUd]=TMd;(r2b(),!q2b&&(q2b=new I2b),r2b(),q2b).b.i&&Yx(a,KLd,(RA(),kMd));Tt(this.ac,a6d);Eb(this.ac,i_d,true)}\nfunction jgd(a){var b,c;if(a.b){return}c=(!a.G&&(a.G=ig(a)),UN(UN(UN(a.G,5),39),152)).d;b=(!a.G&&(a.G=ig(a)),UN(UN(UN(a.G,5),39),152)).c;if(c!=null){b=V_b(a.v,'layouts/'+c+'.html');b==null&&Vt(Y(UN(Qg(a),79)),'<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}b!=null&&xKc(UN(Qg(a),79),b,W_b(a.v));a.b=true}\nfunction tKc(a,b){var c,d,e,f,g,i,j,k;b=$Bd(b,'_UID_',a.i+'__');a.j='';d=0;f=b.toLowerCase();i='';j=f.indexOf('<script',0);while(j>0){i+=dCd(b,d,j);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.j+=dCd(b,j+1,e)+';';g=d=e+9;j=f.indexOf('<script',g)}i+=cCd(b,d);f=i.toLowerCase();k=f.indexOf('<body');if(k<0){i=i}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(i=dCd(i,k,c)):(i=cCd(i,k))}return i}\nfunction xKc(a,b,c){var d;b=tKc(a,b);d=M7b(c+'/layouts/');b=$Bd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',_5d+d+'$3\"');b=$Bd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',_5d+d+'$3\"');b=$Bd(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Vt((otb(),a.ac),b);a.f.Ic();AKc(a,a.ac);wKc(a);a.d=wtb(a.ac);!a.d&&(a.d=a.ac);yKc(a,a.d);a.e=true}\nvar Y5d='childLocations',Z5d='templateName',$5d='templateContents',_5d='<$1 $2src=\"',a6d='v-customlayout';mpb(1735,1,HTd);_.Je=function Pyc(){bCc(this.c,Xjb,mjb);TBc(this.c,sXd,lgb);WBc(this.c,Xjb,xXd,new Qyc);WBc(this.c,lgb,xXd,new Syc);WBc(this.c,pbb,xXd,new Uyc);_Bc(this.c,lgb,DMd,new LBc(pbb));_Bc(this.c,lgb,tMd,new LBc(Xjb));Myc(this.c);ZBc(this.c,Xjb,Y5d,new MBc(qYd,JN(hob,MXd,8,0,[new LBc(Wib),new LBc(Tlb)])));ZBc(this.c,Xjb,Z5d,new LBc(Tlb));ZBc(this.c,Xjb,$5d,new LBc(Tlb));Nkc((!Gkc&&(Gkc=new Tkc),Gkc),this.b.e)};mpb(647,1,d$d,Qyc);_.ck=function Ryc(a,b){return new Vwd};mpb(1199,1,d$d,Syc);_.ck=function Tyc(a,b){return new kgd};mpb(1718,1,d$d,Uyc);_.ck=function Vyc(a,b){return new DKc};mpb(79,HLd,{2129:1,2521:1,1857:1,2411:1,117:1,2389:1,2595:1,319:1,304:1,18:1,79:1,192:1},DKc);_.Hc=function EKc(a){throw new VCd};_.Ic=function FKc(){Ic(this);this.g.Ic();this.b.Ic()};_.Pk=function HKc(){};_.tc=function IKc(a){Rb(this,a);if((otb(),ivb(a))==hQd){o8b(this,true);hvb(a,true)}};_.uc=function JKc(){Sb(this);!!this.d&&(this.d.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function KKc(a){return zKc(this,a)};_.kc=function LKc(a){Tt((otb(),this.ac),a);Eb(this.ac,i_d,true)};_.e=false;mpb(856,1403,{424:1,7:1,716:1,73:1,289:1,67:1,633:1,140:1,194:1,81:1,382:1,135:1,3:1},kgd);_.Od=function lgd(){return !this.G&&(this.G=ig(this)),UN(UN(UN(this.G,5),39),152)};_.Ad=function mgd(){return !this.G&&(this.G=ig(this)),UN(UN(UN(this.G,5),39),152)};_.Ji=function ngd(){return !this.G&&(this.G=ig(this)),UN(UN(UN(this.G,5),39),152)};_.ad=function ogd(){return UN(Qg(this),79)};_.Cd=function pgd(){UN(Qg(this),79).c=this.v;UN(Qg(this),79).i=this.A};_.Qi=function qgd(){vKc((UN(Qg(this),79),wtb(Y(UN(Qg(this),79)))))};_.ke=function rgd(b){var c,d,e,f,g,i;jgd(this);for(d=Qh(this).Pc();d.nh();){c=UN(d.oh(),7);e=UN((!this.G&&(this.G=ig(this)),UN(UN(UN(this.G,5),39),152)).b.Lm(c),2);try{BKc(UN(Qg(this),79),c.ad(),e)}catch(a){a=jpb(a);if(!WN(a,272))throw ipb(a)}}for(g=b.b.Pc();g.nh();){f=UN(g.oh(),7);if(f.yd()==this){continue}i=f.ad();i.rc()&&zKc(UN(Qg(this),79),i)}};_.Ed=function sgd(a){Sg(this,a);jgd(this);GKc(UN(Qg(this),79).j);UN(Qg(this),79).j=null};_.le=function tgd(a){CKc(UN(Qg(this),79),a)};_.ui=function ugd(a,b){};_.b=false;mpb(152,39,{5:1,398:1,39:1,152:1,3:1},Vwd);var Xjb=oAd('com.vaadin.shared.ui.customlayout.','CustomLayoutState',152),lgb=oAd('com.vaadin.client.ui.customlayout.',w$d,856),pbb=oAd(q1d,'VCustomLayout',79),C8=oAd(c2d,'ConnectorBundleLoaderImpl$21$1$1',647),D8=oAd(c2d,'ConnectorBundleLoaderImpl$21$1$2',1199),E8=oAd(c2d,'ConnectorBundleLoaderImpl$21$1$3',1718);kLd(Yq)(21);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-21.js\n")
let nodeBtnDonnerAvis = document.querySelector("#donner-avis");
let nodeFormAvis = document.querySelector("#avis-form");
nodeBtnDonnerAvis.addEventListener("click",()=>{
	const CLASSNAME = "d-none";
	nodeBtnDonnerAvis.innerHTML = (nodeFormAvis.className===CLASSNAME)?"Cacher le formulaire":"Donner votre avis!"
	nodeFormAvis.classList.toggle(CLASSNAME);
});
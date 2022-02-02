package pg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pg.assetModel.AssetInformation;
import pg.assetModel.VendorInformation;
import pg.services.AssetService;
import pg.services.PasswordService;


@Controller
public class AssetProcessController {
	
	@Autowired
	private AssetService assetService;
	

	
	@RequestMapping(value = "asset_process",method=RequestMethod.GET)
	public ModelAndView asset_process(ModelMap map,HttpSession session) {


		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");

		List<VendorInformation> List= assetService.getVendorList();
		List<AssetInformation> assetList= assetService.getAssetList();
		ModelAndView view = new ModelAndView("fixedasset/asset_process");

		view.addObject("vendorList", List);
		view.addObject("assetList", assetList);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);

		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/saveAssetInformation",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveAssetInformation(AssetInformation v)  {

		JSONObject objmain = new JSONObject();
		
		if(!assetService.isAssetExist(v)) {
			if(assetService.saveAsset(v)) {

				JSONArray mainarray = new JSONArray();

				List<AssetInformation> List= assetService.getAssetList();
				objmain.put("result", List);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		
		
		return objmain;
	}
	
	@ResponseBody
	@RequestMapping(value = "/printAssetDetailsReport/{assetId}")
	public ModelAndView printAssetDetailsReport(ModelMap map,@PathVariable ("assetId") String assetId) {
		ModelAndView view = new ModelAndView("fixedasset/assetDetailsReportPreview");
		map.addAttribute("assetId",assetId);
		return view;

	}
}

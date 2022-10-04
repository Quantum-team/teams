const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false
})
module.exports={
	devServer:{
		proxy:{
			'/api':{
				target:'http://124.222.194.119:6383',
				changOrigen:true,
				pathRewrite:{
					'^/api':''
				}
			}
		}
	}
}
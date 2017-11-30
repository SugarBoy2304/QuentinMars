#ifdef GL_ES
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif
varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform int u_render;

float sign (vec2 p1, vec2 p2, vec2 p3)
{
    return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
}

bool PointInTriangle (vec2 pt, vec2 v1, vec2 v2, vec2 v3)
{
    bool b1, b2, b3;

    b1 = sign(pt, v1, v2) < 0.0;
    b2 = sign(pt, v2, v3) < 0.0;
    b3 = sign(pt, v3, v1) < 0.0;

    return ((b1 == b2) && (b2 == b3));
}

void main() {   
   
	//как и в стандартном шейдере получаем итоговый цвет пикселя
    gl_FragColor = (v_color * texture2D(u_texture, v_texCoords));
	
	if (u_render == 1) {
   
    //после получения итогового цвета, меняем его на противоположный
	//gl_FragColor.rgb=vec4(1, 1, 1, 0.75);
	
		if (PointInTriangle(v_texCoords, vec2(0.2, 0.2), vec2(0.20, 0.7), vec2(0.7, 0.20))) {
			gl_FragColor.rgb=gl_FragColor * vec4(1, 1, 1, 0.75);
		} else {
			gl_FragColor.rgb=gl_FragColor * vec4(0.15, 0.15, 0.15, 0.4);
		}
	
	} else {
		//gl_FragColor.rgb=gl_FragColor * vec4(1, 0, 0, 0.4);
	}
}

